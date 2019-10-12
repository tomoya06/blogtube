package com.heavelop.blogtube.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommonFetchPaginationResult<T> {
  private Integer total;
  private Integer size;
  private Integer from;
  private List<T> data;
}