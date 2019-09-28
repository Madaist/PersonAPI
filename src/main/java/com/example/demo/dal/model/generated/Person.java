package com.example.demo.dal.model.generated;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Person
 */

public class Person   {
    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("socialSecurityCode")
    private String socialSecurityCode;

    public Person lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Get lastName
     * @return lastName
     */
    @ApiModelProperty(example = "Istrate", value = "")


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Person firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Get firstName
     * @return firstName
     */
    @ApiModelProperty(example = "Madalina", value = "")


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Person age(Integer age) {
        this.age = age;
        return this;
    }

    /**
     * Get age
     * @return age
     */
    @ApiModelProperty(example = "20", value = "")


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Person socialSecurityCode(String socialSecurityCode) {
        this.socialSecurityCode = socialSecurityCode;
        return this;
    }

    /**
     * Get socialSecurityCode
     * @return socialSecurityCode
     */
    @ApiModelProperty(example = "123456789", value = "")


    public String getSocialSecurityCode() {
        return socialSecurityCode;
    }

    public void setSocialSecurityCode(String socialSecurityCode) {
        this.socialSecurityCode = socialSecurityCode;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(this.lastName, person.lastName) &&
                Objects.equals(this.firstName, person.firstName) &&
                Objects.equals(this.age, person.age) &&
                Objects.equals(this.socialSecurityCode, person.socialSecurityCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, age, socialSecurityCode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Person {\n");

        sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
        sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
        sb.append("    age: ").append(toIndentedString(age)).append("\n");
        sb.append("    socialSecurityCode: ").append(toIndentedString(socialSecurityCode)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
