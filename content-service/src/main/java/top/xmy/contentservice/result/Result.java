package top.xmy.contentservice.result;

public class Result<T> {
    private T data;
    private boolean success;

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.data = data;
        result.success = true;
        return result;
    }

    public T getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }
}