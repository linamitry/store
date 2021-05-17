package org.example.store.model;

public class User {
    private int id;
    private String email;
    private String password;
    private String role;

    private User(){}

    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getRole() {
        return role;
    }

    public static Builder newBuilder(){
        return new User().new Builder();
    }

    public class Builder{
        private Builder(){}

        public Builder setId(int id){
            User.this.id = id;
            return this;
        }
        public Builder setEmail(String email){
            User.this.email = email;
            return this;
        }
        public Builder setPassword(String password){
            User.this.password = password;
            return this;
        }
        public Builder setRole(String role){
            User.this.role = role;
            return this;
        }
        public User build(){
            return User.this;
        }
    }
}
