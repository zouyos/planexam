package cda.greta94.planexam.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PasswordDto {
    private String token;
    @NotBlank
    @Size(min = 8)
    private String mdp1;
    @NotBlank
    @Size(min = 8)
    private String mdp2;

    public PasswordDto() {
    }

    public PasswordDto(String token) {
        this.token = token;
    }

    public PasswordDto(String token, String mdp1, String mdp2) {
        this.token = token;
        this.mdp1 = mdp1;
        this.mdp2 = mdp2;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMdp1() {
        return mdp1;
    }

    public void setMdp1(String mdp1) {
        this.mdp1 = mdp1;
    }

    public String getMdp2() {
        return mdp2;
    }

    public void setMdp2(String mdp2) {
        this.mdp2 = mdp2;
    }
}
