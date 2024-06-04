package org.example.vo;

import lombok.Data;
import org.example.dto.ExecutiveDTO;
import org.example.dto.PresidentDTO;
import org.example.dto.TotalMemberDTO;

import java.util.List;

@Data
public class MemberVO {
    private PresidentDTO president;
    private List<ExecutiveDTO> executives;
    private TotalMemberDTO others;
    public MemberVO(PresidentDTO president, List<ExecutiveDTO> executives, TotalMemberDTO others)
    {
        this.president = president;
        this.executives = executives;
        this.others = others;
    }

}
