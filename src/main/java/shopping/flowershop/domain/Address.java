package shopping.flowershop.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
public class Address {
    private String basicAddress;
    private String detailedAddress;
    @Column(length = 5)
    private String zipcode;

    protected Address(){

    }
    @Builder
    public Address(String basicAddress,String detailedAddress,String zipcode){
        this.basicAddress = basicAddress;
        this.detailedAddress = detailedAddress;
        this.zipcode = zipcode;
    }

    public String fullAddress(){
        return getBasicAddress() + ", " + getDetailedAddress();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return  Objects.equals(getBasicAddress(), address.getBasicAddress()) &&
                Objects.equals(getDetailedAddress(), address.getDetailedAddress()) &&
                Objects.equals(getZipcode(), address.getZipcode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBasicAddress(), getDetailedAddress(), getZipcode());
    }

}
