package com.projetofinal.projetofinal.dtos.FinancialGoal;

import java.time.LocalDate;

import com.projetofinal.projetofinal.model.User.User;

public class FinancialGoalRequestDto {
        private Double amount;
        private LocalDate creationDate;
        private String description;
        private Integer userid;
        private LocalDate deadline;
        private Double saved;
        private String name;
        private Integer type;
        @SuppressWarnings("unused")
        private User user;

        public FinancialGoalRequestDto() {
        }

        public FinancialGoalRequestDto(Double amount, LocalDate creationDate, String description, Integer userid,
                        LocalDate deadline, Double saved, String name, Integer type) {
                this.amount = amount;
                this.creationDate = creationDate;
                this.description = description;
                this.userid = userid;
                this.deadline = deadline;
                this.saved = saved;
                this.name = name;
                this.type = type;
        }

        // Getters and setters for all properties

        public void setUser(User user) {
                this.user = user;
        }

        public Double getAmount() {
                return amount;
        }

        public void setAmount(Double amount) {
                this.amount = amount;
        }

        public LocalDate getCreationDate() {
                return creationDate;
        }

        public void setCreationDate(LocalDate creationDate) {
                this.creationDate = creationDate;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public Integer getUserid() {
                return userid;
        }

        public void setUserid(Integer userid) {
                this.userid = userid;
        }

        public LocalDate getDeadline() {
                return deadline;
        }

        public void setDeadline(LocalDate deadline) {
                this.deadline = deadline;
        }

        public Double getSaved() {
                return saved;
        }

        public void setSaved(Double saved) {
                this.saved = saved;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public Integer getType() {
                return type;
        }

        public void setType(Integer type) {
                this.type = type;
        }

}
