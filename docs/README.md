# Rainbow User Guide

![Rainbow Chatbot](https://via.placeholder.com/600x300?text=Rainbow+Task+Manager)

Rainbow is a task management chatbot that helps you keep track of your todos, deadlines, and events through a simple command-line interface. All your tasks are automatically saved to disk, so you never lose your progress!

## Features

### Adding Todos: `todo`

Adds a simple task without any date/time attached to it.

**Format:** `todo DESCRIPTION`

**Example:** `todo read book`

**Expected output:**
```
____________________________________________________________
 Got it. I've added this task:
   [T][ ] read book
 Now you have 1 tasks in the list.
____________________________________________________________
```

---

### Adding Deadlines: `deadline`

Adds a task that needs to be done by a specific date/time.

**Format:** `deadline DESCRIPTION /by DATE_TIME`

**Supported date formats:**
- `yyyy-MM-dd` (e.g., 2019-12-02)
- `d/M/yyyy` (e.g., 2/12/2019)
- With time: `yyyy-MM-dd HHmm` or `d/M/yyyy HHmm` (e.g., 2/12/2019 1800)

**Example:** `deadline return book /by 2019-12-02`

**Expected output:**
```
____________________________________________________________
 Got it. I've added this task:
   [D][ ] return book (by: Dec 2 2019)
 Now you have 2 tasks in the list.
____________________________________________________________
```

**Example with time:** `deadline submit assignment /by 2/12/2019 1800`

**Expected output:**
```
____________________________________________________________
 Got it. I've added this task:
   [D][ ] submit assignment (by: Dec 2 2019, 6:00PM)
 Now you have 3 tasks in the list.
____________________________________________________________
```

---

### Adding Events: `event`

Adds a task that happens during a specific time period.

**Format:** `event DESCRIPTION /from START_TIME /to END_TIME`

**Example:** `event project meeting /from Mon 2pm /to 4pm`

**Expected output:**
```
____________________________________________________________
 Got it. I've added this task:
   [E][ ] project meeting (from: Mon 2pm to: 4pm)
 Now you have 4 tasks in the list.
____________________________________________________________
```

**Example with dates:** `event workshop /from 2019-08-06 1400 /to 2019-08-06 1600`

**Expected output:**
```
____________________________________________________________
 Got it. I've added this task:
   [E][ ] workshop (from: Aug 6 2019, 2:00PM to: Aug 6 2019, 4:00PM)
 Now you have 5 tasks in the list.
____________________________________________________________
```

---

### Listing All Tasks: `list`

Shows all tasks in your task list.

**Format:** `list`

**Expected output:**
```
____________________________________________________________
 Here are the tasks in your list:
 1.[T][ ] read book
 2.[D][ ] return book (by: Dec 2 2019)
 3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
____________________________________________________________
```

---

### Marking Tasks as Done: `mark`

Marks a task as completed.

**Format:** `mark INDEX`

**Example:** `mark 1`

**Expected output:**
```
____________________________________________________________
 Nice! I've marked this task as done:
   [T][X] read book
____________________________________________________________
```

---

### Unmarking Tasks: `unmark`

Marks a task as not done yet.

**Format:** `unmark INDEX`

**Example:** `unmark 1`

**Expected output:**
```
____________________________________________________________
 OK, I've marked this task as not done yet:
   [T][ ] read book
____________________________________________________________
```

---

### Deleting Tasks: `delete`

Removes a task from your task list.

**Format:** `delete INDEX`

**Example:** `delete 3`

**Expected output:**
```
____________________________________________________________
 Noted. I've removed this task:
   [E][ ] project meeting (from: Mon 2pm to: 4pm)
 Now you have 2 tasks in the list.
____________________________________________________________
```

---

### Finding Tasks by Keyword: `find`

Searches for tasks that contain a specific keyword in their description. The search is case-insensitive.

**Format:** `find KEYWORD`

**Example:** `find book`

**Expected output:**
```
____________________________________________________________
 Here are the matching tasks in your list:
 1.[T][X] read book
 2.[D][ ] return book (by: Dec 2 2019)
____________________________________________________________
```

---

### Exiting the Program: `bye`

Exits the Rainbow chatbot.

**Format:** `bye`

**Expected output:**
```
____________________________________________________________
 Bye. Hope to see you again soon!
____________________________________________________________
```

---

## Task Type Indicators

- `[T]` - Todo: A simple task
- `[D]` - Deadline: A task with a deadline
- `[E]` - Event: A task that happens during a time period

## Task Status Indicators

- `[ ]` - Task not completed yet
- `[X]` - Task completed

## Data Storage

All your tasks are automatically saved to `./data/duke.txt` whenever you make changes. The tasks will be loaded automatically when you start Rainbow again, so you never lose your progress!

## Error Handling

Rainbow will display helpful error messages if:
- You try to add a task without a description
- You provide an invalid task number
- You use an unknown command
- You forget to specify which task to delete

Simply follow the error message instructions to correct your input!

