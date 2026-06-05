package features.user.domain;

public class UserResponseDTO {
    private int status;
    private String message;

        public UserResponseDTO() {
    }

    public UserResponseDTO(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserResponseDTO [status=" + status + ", message=" + message + "]";
    }
    
}
