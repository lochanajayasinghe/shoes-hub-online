package com.PeraUni.HMS_Demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "damages")  // Ensuring table name consistency
public class Damages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "damage_id")
    private Long damage_Id;  // Changed from `damage_Id` to `damageId` (consistent naming)

    @NotEmpty(message = "Registration number is required")
    @Column(name = "reg_no", nullable = false)
    private String regNo;

    @NotEmpty(message = "Room ID is required")
    @Column(name = "room_id", nullable = false)
    private String roomId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero")
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @NotEmpty(message = "Description is required")
    @Size(min = 3, message = "Description should be at least 3 characters")
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull(message = "Damage Date is required")
    @Column(name = "damage_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate damageDate;


    @Column(name = "settle status", nullable = false)
    private boolean settled; // Default to 'false' (unsettled)


    // Getters and Setters (corrected naming convention)
    public Long getDamage_Id() {
        return damage_Id;
    }

    public void setDamage_Id(Long damage_Id) {
        this.damage_Id = damage_Id;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDamageDate() {
        return damageDate;
    }

    public void setDamageDate(LocalDate damageDate)
    {
        this.damageDate = damageDate;
    }

    public boolean isSettled() {
        return settled;
    }

    public void setSettled(boolean settled) {

        this.settled= settled;
    }
}
