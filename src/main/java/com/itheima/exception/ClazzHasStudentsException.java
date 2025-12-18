package com.itheima.exception;

public class ClazzHasStudentsException extends RuntimeException {

    public ClazzHasStudentsException() {
        super("该班级下仍存在学生，无法删除");
    }

    public ClazzHasStudentsException(String message) {
        super(message);
    }
}
