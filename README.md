# JCron
## Overview
JCron is a simple and efficient cron implemented in Java.

<br>

## Motivation
I needed a process to perform orchestration within containers.

The native cron utility made for an easy solution, but I did not like that it was privileged, especially when privileges were not required, so I created JCron. (I tend to reserve user namespace mapping for the processes that absolutely require it.)

<br>

## Example Cron Table
```
# This is an example of a valid cron table, formatted in JSON.
# There are currently three schedule modes.
# 1) A job can run immediately upon cron initialization (e.g., "schedule":"init").
# 2) A job can run at a certain frequency in minutes (e.g., "schedule":"5").
# 3) A job can run at a certain time in the day (e.g., "schedule":"17:30").

[
   {
      "job": "/scripts/restore.sh",
      "arguments": [""],
      "schedule": "init"
   },
   {
      "job": "/scripts/backup.sh",
      "arguments": [""],
      "schedule": "5"
   },
   {
      "job": "/scripts/restart.sh",
      "arguments": ["The server will restart in one minute."],
      "schedule": "23:59"
   },
   {
      "job": "/usr/bin/docker",
      "arguments": ["run", "--rm", "name:tag"],
      "schedule": "60"
   }
]
```
