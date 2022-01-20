package com.gmail.serhiiemiv.tdo;

import com.gmail.serhiiemiv.modeles.CostumerFeedback;
import com.gmail.serhiiemiv.modeles.Order;
import com.gmail.serhiiemiv.modeles.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostumerTdo {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int phone;
    private int userId;
    private List<Integer> ordersId;
    private List<Integer> feedbacksId;
}
