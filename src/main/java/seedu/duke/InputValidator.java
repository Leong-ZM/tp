package seedu.duke;

import seedu.duke.exceptions.InvalidDayException;
import seedu.duke.exceptions.InvalidFormatException;
import seedu.duke.exceptions.InvalidUserException;

public class InputValidator {
    /**
     * Validates correctly formatted compare command. The expected format is
     * "compare user1 user2"
     *
     * @param input String Input.
     * @throws InvalidFormatException If the input does not match the expected format: "compare (user1) (user2)".
     */
    public static void validateCompareInput(String input) throws InvalidFormatException {
        String regex = "(?i)^compare\\s+\\w+\\s+\\w+(\\s+)?$";

        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid compare format. " +
                    "Expected format: compare <user1> <user2>");
        }
    }

    public static void validateCompareAllInput(String input) throws InvalidFormatException {
        String regex = "(?i)^compareall(\\s+)?$";

        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid compareall format. " +
                    "Expected format: compareall");
        }
    }

    /**
     * Validates correctly formatted addTask command. The expected format is
     * "addtask /on [date] /task [description] /from [start time] /to [start time] /type [type]"
     * Note: Start and End times should be formatted as such: HH:mm
     * Regex Guide:
     * (?i)^ = case insensitive for command name
     * \s+ = any amount of white space
     * (\w+) = any non-digit word
     * ((.+)?) = any combination of non numerical and numerical characters
     * (\d{1,2}:\d{2}) = the format DD:DD (D = digit)
     * (/type\s+[fc](\s+)?)$ = regex must end in "/type [f/c]"
     *
     * @param input String Input.
     * @throws InvalidFormatException If the input does not match the expected format.
     */
    public static void validateAddTaskInput(String input) throws InvalidFormatException {
        String regex = "(?i)^addtask\\s+/on\\s+(\\w+)\\s+/task\\s+((.+)?)\\s" +
                "/from\\s+(\\d{1,2}:\\d{2})\\s+/to\\s+(\\d{1,2}:\\d{2})\\s+(/type\\s+[fc](\\s+)?)$";

        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid addtask format. " +
                    "Expected format: addtask /on [day] /task [description] /from [start time] /to [end time] " +
                    "/type [f/c]");
        }
    }
    public static void validateAddTaskWDCInput(String input) throws InvalidFormatException {
        String regex = "(?i)^addtwdc\\s+/on\\s+(\\w+)\\s+/task\\s+(.+?)\\s" +
                "+/from\\s+(\\d{1,2}:\\d{2})\\s+/to\\s+(\\d{1,2}:\\d{2})(\\s+/type\\s+[fc])$";

        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid addTaskWithDuplicationCheck format. " +
                    "Expected format: addtwdc /on [day] /task [description] /from [start time] /to [end time] " +
                    "/type [f/c]");
        }
    }

    /**
     * Validates correctly formatted deleteTask command. The expected format is
     * "deletetask /on [day] /index [index]" format
     * Note: index is the index of the task in the task list for the given day
     *
     * @param input String Input.
     * @throws InvalidFormatException If the input does not match the expected format.
     */
    public static void validateDeleteTaskInput(String input) throws InvalidFormatException {
        String regex = "(?i)^deletetask\\s+/on\\s+(\\w+)\\s+/index\\s+(\\d+)$";

        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid deleteTask format. " +
                    "Expected format: deleteTask /on [day] /index [index]");
        }
    }

    /**
     * Validates correctly formatted addUser command. The expected format is "adduser user"
     *
     * @param input String Input.
     * @throws InvalidFormatException If the input does not match the expected format: "adduser (user)".
     */
    public static void validateAddUserInput(String input) throws InvalidFormatException {
        String regex = "(?i)^adduser\\s+\\w+(\\s+)?$";

        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid addUser format. " +
                    "Expected format: adduser <desired user's name>");
        }
    }

    /**
     * Validates correctly formatted switch command. The expected format is "switch user"
     *
     * @param input String Input.
     * @throws InvalidFormatException If the input does not match the expected format: "switch (user)".
     */
    public static void validateSwitchInput(String input) throws InvalidFormatException {
        String regex = "(?i)^switch\\s+\\w+$";

        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid switch format. " +
                    "Expected format: switch <desired user's name>");
        }
    }

    /**
     * Validates correctly spelled usernames that currently exist in the user database.
     *
     * @param input String Input.
     * @throws InvalidUserException If the input does not match any current user's name in the user database.
     */
    public static void validateUserInput(String input, UserList userList) throws InvalidUserException {
        if (userList.getUsers().isEmpty()) {
            throw new InvalidUserException("[ERROR] Current User List is empty. Please add users.");
        }
        for (User u : userList.getUsers()) {
            if (u.getName().equals(input)) {
                return;
            }
        }
        throw new InvalidUserException("[ERROR] Invalid User: " + input + ". Please input a existing user name");
    }

    /**
     * Validates if the inputted string is an actual day.
     *
     * @param input String Input.
     * @throws InvalidDayException If the input is not an actual day.
     */
    public static void validateDay(String input) throws InvalidDayException {
        String[] validDays = new String[]{"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};

        for (String day : validDays) {
            if (day.equalsIgnoreCase(input)) {
                return;
            }
        }
        throw new InvalidDayException("[ERROR] Invalid day. Please enter a day from Monday - Sunday.");
    }

    /**
     * Validates whether a Timetable exist or not.
     *
     * @param table inputted Timetable.
     * @throws NullPointerException If the input Timetable does not exist.
     */
    public static void validateTableExistence(Timetable table) throws NullPointerException {
        if (table == null) {
            throw new NullPointerException("Timetable object is null.");
        }
    }

    public static void validateChangeTaskTiming(String input) throws InvalidFormatException {
        String prefix = "(?i)^changeTaskTiming\\s+/on\\s+";
        String dayPattern = "(?:Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday)";
        String indexPattern = "\\d+";
        String startPattern = "\\d{1,2}:\\d{2}";
        String endPattern = "\\d{1,2}:\\d{2}";
        String suffix = "$";
        String regex = prefix + dayPattern + "\\s+/index\\s+" + indexPattern + "\\s+/from\\s+" +
                startPattern + "\\s+/to\\s+" + endPattern + suffix;
        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid changeTaskTiming format. " +
                    "Expected format: changeTaskTiming /on [day] /index [index] /from [new start time] " +
                    "/to [new end time]");
        }
    }

    public static void validateAddTaskForAll(String input) throws InvalidFormatException {
        String regex = "(?i)^addforall\\s+/on\\s+(\\w+)\\s+/task\\s+((.+)?)\\s" +
                "/from\\s+(\\d{1,2}:\\d{2})\\s+/to\\s+(\\d{1,2}:\\d{2})\\s?";

        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid addforall format. " +
                    "Expected format: addforall /on [day] /task [description] /from [start time] /to [end time]");
        }
    }

    public static void validateChangeTaskType(String input) throws InvalidFormatException {
        String prefix = "(?i)^changeTaskType\\s+/on\\s+";
        String dayPattern = "(?:Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday)";
        String indexPattern = "\\d+";
        String typePattern = "[fc]"; // Assuming task types can be 'f' for flexible and 'c' for compulsory
        String suffix = "$";
        String regex = prefix + dayPattern + "\\s+/index\\s+" + indexPattern + "\\s+/type\\s+" + typePattern + suffix;
        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid changeTaskType format. " +
                    "Expected format: changeTaskType /on [day] /index [index] /type [f/c]");
        }
    }

    public static void validateAddRepeatTask(String input) throws InvalidFormatException {
        String regex = "(?i)^addrepeattask\\s+/task\\s+(.+?)\\s+/on\\s+(\\w+(\\s+\\w+)*)\\s+" +
                "/from\\s+(\\d{1,2}:\\d{2})\\s+/to\\s+(\\d{1,2}:\\d{2})\\s+/type\\s+([fc])$";

        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid addRepeatTask format. " +
                    "Expected format: addRepeatTask /task [description] /on [day(s)] /from [start time] " +
                    "/to [end time] /type [f/c]");
        }
    }

    public static void validAddFor(String input) throws InvalidFormatException {
        String commandPattern = "(?i)addfor\\s+";
        String usersPattern = "/user\\s+([\\w\\s,]+)\\s+";
        String dayPattern = "/on\\s+(monday|tuesday|wednesday|thursday|friday|saturday|sunday)\\s+";
        String taskDescriptionPattern = "(?i)/task\\s+([\\w\\s]+)\\s+";
        String startPattern = "/from\\s+(\\d{1,2}:\\d{2})\\s+";
        String endPattern = "/to\\s+(\\d{1,2}:\\d{2})\\s+";
        String typePattern = "/type\\s+([cfCF])";
        String suffix = "$";
        String pattern = commandPattern + usersPattern + dayPattern + taskDescriptionPattern
            + startPattern + endPattern + typePattern + suffix;

        if (!input.matches(pattern)) {
            throw new InvalidFormatException("[ERROR] Invalid addfor format. " +
                "Expected format: addfor /user [user1], [user2], ... /on [day] /task [description] " +
                "/from [start time] /to [end time] /type [f/c]");
        }
    }
}

