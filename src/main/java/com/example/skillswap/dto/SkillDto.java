
package com.example.skillswap.dto;

public class SkillDto {
  private Long id;
  private String name;
  private String category;

  public SkillDto(Long id, String name, String category) {
    this.id = id;
    this.name = name;
    this.category = category;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getCategory() {
    return category;
  }

}
