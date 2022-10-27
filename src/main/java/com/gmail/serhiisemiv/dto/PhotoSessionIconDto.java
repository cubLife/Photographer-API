package com.gmail.serhiisemiv.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhotoSessionIconDto {
    private int id;
    private int PhotoSessionId;
}
