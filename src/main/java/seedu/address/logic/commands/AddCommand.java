package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACTIVITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.activity.Activity;
import seedu.address.model.person.Address;
import seedu.address.model.person.ClassCode;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Status;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " Command: Adds a person to the address book. "
            + "\nParameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_STATUS + "STATUS "
            + PREFIX_CLASSCODE + "CLASSCODE "
            + "[" + PREFIX_ACTIVITY + "ACTIVITIES]..."
            + "\n\n" + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Candice N Utz "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "candicenuts@example.com "
            + PREFIX_ADDRESS + "123, Sunrise Road, #01-25 "
            + PREFIX_STATUS + "Negative "
            + PREFIX_CLASSCODE + "4A "
            + PREFIX_ACTIVITY + "Basketball "
            + PREFIX_ACTIVITY + "Dance";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private static final Logger logger = LogsCenter.getLogger(AddCommand.class);
    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        ObservableList<Person> studentList = model.getAddressBook().getPersonList();

        model.addPerson(toAdd);

        try {
            batchUpdateNegativeToPositive(toAdd, studentList, model);
        } catch (Exception ex) {
            logger.severe("Batch update failed: " + StringUtil.getDetails(ex));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    /**
     * Batch updates the list when a new person's with positive status joins the class and/or activity
     */
    private static void batchUpdateNegativeToPositive(Person addedPerson,
                                                      ObservableList<Person> studentList,
                                                      Model model) {

        if (addedPerson.getStatus().toString().equals(Status.POSITIVE)) {

            List<Person> filteredByClassCodeList = studentList.stream()
                    .filter(student -> (student.getClassCode().toString().equals(addedPerson.getClassCode().toString())
                            || student.hasSameActivity(addedPerson))
                            && !student.isSamePerson(addedPerson)
                            && !student.getStatus().toString().equals(Status.POSITIVE))
                    .collect(Collectors.toList());

            for (int i = 0; i < filteredByClassCodeList.size(); i++) {
                Person currentPerson = filteredByClassCodeList.get(i);
                EditCommand.EditPersonDescriptor tempDescriptor = new EditCommand.EditPersonDescriptor();
                tempDescriptor.setStatus(new Status(Status.CLOSE_CONTACT));
                Person editedPersonStatus = createEditedPerson(currentPerson, tempDescriptor);
                model.setPerson(currentPerson, editedPersonStatus);
            }
        } else {
            List<Person> filteredByClassCodeAndActivityList = studentList.stream()
                    .filter(student -> (student.getClassCode().toString()
                            .equals(addedPerson.getClassCode().toString())
                            || student.hasSameActivity(addedPerson))
                            && !student.isSamePerson(addedPerson))
                    .collect(Collectors.toList());

            for (int i = 0; i < filteredByClassCodeAndActivityList.size(); i++) {
                Person currentPerson = filteredByClassCodeAndActivityList.get(i);

                List<Person> positiveRelatedToPerson = studentList.stream()
                        .filter(student -> (student.getClassCode().toString()
                                .equals(currentPerson.getClassCode().toString())
                                || student.hasSameActivity(currentPerson))
                                && !student.isSamePerson(addedPerson)
                                && student.getStatus().toString().equals(Status.POSITIVE))
                        .collect(Collectors.toList());

                if (positiveRelatedToPerson.size() == 0) {
                    EditCommand.EditPersonDescriptor tempDescriptor = new EditCommand.EditPersonDescriptor();
                    tempDescriptor.setStatus(new Status(Status.NEGATIVE));
                    Person editedPersonStatus = createEditedPerson(currentPerson, tempDescriptor);
                    model.setPerson(currentPerson, editedPersonStatus);
                } else {
                    EditCommand.EditPersonDescriptor tempDescriptor = new EditCommand.EditPersonDescriptor();
                    tempDescriptor.setStatus(new Status(Status.CLOSE_CONTACT));
                    Person editedPersonStatus = createEditedPerson(addedPerson, tempDescriptor);
                    model.setPerson(addedPerson, editedPersonStatus);
                }
            }
        }
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit,
                                             EditCommand.EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(personToEdit);

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Status updatedStatus = editPersonDescriptor.getStatus().orElse(personToEdit.getStatus());
        ClassCode updatedClassCode = editPersonDescriptor.getClassCode().orElse(personToEdit.getClassCode());
        Set<Activity> updatedActivity = editPersonDescriptor.getActivities().orElse(personToEdit.getActivities());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedStatus,
                updatedClassCode, updatedActivity);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
