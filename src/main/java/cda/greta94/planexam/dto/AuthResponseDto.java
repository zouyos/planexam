package cda.greta94.planexam.dto;

public class AuthResponseDto {
  private String token;

  public AuthResponseDto(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }
}
