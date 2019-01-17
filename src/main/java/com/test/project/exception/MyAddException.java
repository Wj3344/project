package com.test.project.exception;

/**
 * Created by handsome programmer.
 *
 * Date: 19-1-10
 * Time: 下午2:50
 * Description: 自定义添加失败异常类
 *
 *
 */
public class MyAddException extends RuntimeException {
    public MyAddException() {
        super();
    }

    public MyAddException(String message) {
        super(message);
    }
}
