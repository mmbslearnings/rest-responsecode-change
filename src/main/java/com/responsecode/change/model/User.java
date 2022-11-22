package com.responsecode.change.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is User model class.
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User{
    public int id;
    public String name;
    public String email;
    public String gender;
    public String status;
}

