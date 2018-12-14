package org.sct.forge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author SCT_Alchemy
 * @date 2018/12/13 2:43 PM
 */

@Data
@AllArgsConstructor
public class ForgeData {
    String GuiName;
    String item;
    int exp;
    int add;
    int slot;
    List<String> require;
    List<String> reward;
}
