package com.heavelop.blogtube.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class DialogueFull extends Dialogue {
  private List<Bravo> bravos;
}