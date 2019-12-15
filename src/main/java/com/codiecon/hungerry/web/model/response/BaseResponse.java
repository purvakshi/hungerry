package com.codiecon.hungerry.web.model.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> implements Serializable {
  private List<ErrorResponse> errors;
  private T data;
  public BaseResponse(T data) {
    this.data = data;
  }
  public BaseResponse(List<ErrorResponse> errors) {
    this.errors = errors;
  }
}
