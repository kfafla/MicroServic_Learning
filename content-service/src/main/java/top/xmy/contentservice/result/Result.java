package top.xmy.contentservice.result;

public class Result<T> {
    private T data;
    private boolean success;
    private String errorMessage;

    // 成功的静态方法
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.data = data;
        result.success = true;
        return result;
    }

    // 失败的静态方法
    public static <T> Result<T> error(String errorMessage) {
        Result<T> result = new Result<>();
        result.success = false;
        result.errorMessage = errorMessage;
        return result;
    }

    public T getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}