package com.taskflow.modernjava;

public sealed interface TaskStatus
        permits Todo, InProgress, Done{
}
