package edu.epam.fop.lambdas;
import edu.epam.fop.lambdas.Address;
import edu.epam.fop.lambdas.Company;
import edu.epam.fop.lambdas.Person;

import java.util.Comparator;

public interface Comparators {

  // ZIP_CODE_COMPARATOR
  static Comparator<Address> ZIP_CODE_COMPARATOR = Comparator
      .comparing(Address::getZipCode, Comparator.nullsFirst(Comparator.reverseOrder()));

  // STREET_COMPARATOR
  static Comparator<Address> STREET_COMPARATOR = Comparator
      .comparing(Address::getStreet, Comparator.nullsLast(Comparator.naturalOrder()))
      .thenComparing(Address::getBuilding, Comparator.nullsLast(Comparator.naturalOrder()))
      .thenComparing(Address::getApartment, Comparator.nullsLast(Comparator.naturalOrder()));

  // REGION_COMPARATOR
  static Comparator<Address> REGION_COMPARATOR = Comparator
      .comparing(Address::getCountry, Comparator.nullsLast(Comparator.naturalOrder()))
      .thenComparing(Address::getCity, Comparator.nullsLast(Comparator.naturalOrder()))
      .thenComparing(Address::getZipCode, Comparator.nullsFirst(Comparator.reverseOrder()));

  // ADDRESS_COMPARATOR
  static Comparator<Address> ADDRESS_COMPARATOR = Comparator
      .comparing(Address::getCountry, Comparator.nullsLast(Comparator.naturalOrder()))
      .thenComparing(Address::getCity, Comparator.nullsLast(Comparator.naturalOrder()))
      .thenComparing(Address::getZipCode, Comparator.nullsFirst(Comparator.reverseOrder()))
      .thenComparing(Address::getStreet, Comparator.nullsLast(Comparator.naturalOrder()))
      .thenComparing(Address::getBuilding, Comparator.nullsLast(Comparator.naturalOrder()))
      .thenComparing(Address::getApartment, Comparator.nullsLast(Comparator.naturalOrder()));

  // FULL_NAME_COMPARATOR
  static Comparator<Person> FULL_NAME_COMPARATOR = Comparator
      .comparing(Person::getName, Comparator.nullsLast(Comparator.naturalOrder()))
      .thenComparing(Person::getSurname, Comparator.nullsLast(Comparator.naturalOrder()));

  // BIRTHDATE_COMPARATOR
  static Comparator<Person> BIRTHDATE_COMPARATOR = Comparator
      .comparing(Person::getBirthdate, Comparator.nullsLast(Comparator.reverseOrder()));

  // PERSON_COMPARATOR
  static Comparator<Person> PERSON_COMPARATOR = Comparator
      .comparing(Person::getName, Comparator.nullsLast(Comparator.naturalOrder()))
      .thenComparing(Person::getSurname, Comparator.nullsLast(Comparator.naturalOrder()))
      .thenComparing(Person::getBirthdate, Comparator.nullsLast(Comparator.reverseOrder()))
      .thenComparing(p -> p.getAddress() != null ? p.getAddress().getCountry() : null, Comparator.nullsLast(Comparator.naturalOrder()))
      .thenComparing(p -> p.getAddress() != null ? p.getAddress().getCity() : null, Comparator.nullsLast(Comparator.naturalOrder()))
      .thenComparing(p -> p.getAddress() != null ? p.getAddress().getZipCode() : null, Comparator.nullsFirst(Comparator.reverseOrder()))
      .thenComparing(p -> p.getAddress() != null ? p.getAddress().getStreet() : null, Comparator.nullsLast(Comparator.naturalOrder()))
      .thenComparing(p -> p.getAddress() != null ? p.getAddress().getBuilding() : null, Comparator.nullsLast(Comparator.naturalOrder()))
      .thenComparing(p -> p.getAddress() != null ? p.getAddress().getApartment() : null, Comparator.nullsLast(Comparator.naturalOrder()));

  // REGISTRATION_ID_COMPARATOR
  static Comparator<Company> REGISTRATION_ID_COMPARATOR = Comparator
      .comparing(Company::getRegistrationUuid, Comparator.nullsLast(Comparator.naturalOrder()));

  // COMPANY_COMPARATOR
  static Comparator<Company> COMPANY_COMPARATOR = Comparator
      .comparing(Company::getName, Comparator.nullsLast(Comparator.naturalOrder()))
      .thenComparing(Company::getRegistrationUuid, Comparator.nullsLast(Comparator.naturalOrder()))
      .thenComparing(c -> c.getDirector() != null ? c.getDirector().getName() : null, Comparator.nullsLast(Comparator.naturalOrder()))
      .thenComparing(c -> c.getDirector() != null ? c.getDirector().getSurname() : null, Comparator.nullsLast(Comparator.naturalOrder()))
      .thenComparing(c -> c.getDirector() != null ? c.getDirector().getBirthdate() : null, Comparator.nullsLast(Comparator.reverseOrder()))
      .thenComparing(c -> c.getDirector() != null && c.getDirector().getAddress() != null ? c.getDirector().getAddress().getCountry() : null, Comparator.nullsLast(Comparator.naturalOrder()))
      .thenComparing(c -> c.getDirector() != null && c.getDirector().getAddress() != null ? c.getDirector().getAddress().getCity() : null, Comparator.nullsLast(Comparator.naturalOrder()))
      .thenComparing(c -> c.getDirector() != null && c.getDirector().getAddress() != null ? c.getDirector().getAddress().getZipCode() : null, Comparator.nullsFirst(Comparator.reverseOrder()))
      .thenComparing(c -> c.getDirector() != null && c.getDirector().getAddress() != null ? c.getDirector().getAddress().getStreet() : null, Comparator.nullsLast(Comparator.naturalOrder()))
      .thenComparing(c -> c.getDirector() != null && c.getDirector().getAddress() != null ? c.getDirector().getAddress().getBuilding() : null, Comparator.nullsLast(Comparator.naturalOrder()))
      .thenComparing(c -> c.getDirector() != null && c.getDirector().getAddress() != null ? c.getDirector().getAddress().getApartment() : null, Comparator.nullsLast(Comparator.naturalOrder()))
      .thenComparing(c -> c.getOfficeAddress() != null ? c.getOfficeAddress().getCountry() : null, Comparator.nullsLast(Comparator.naturalOrder()))
      .thenComparing(c -> c.getOfficeAddress() != null ? c.getOfficeAddress().getCity() : null, Comparator.nullsLast(Comparator.naturalOrder()))
      .thenComparing(c -> c.getOfficeAddress() != null ? c.getOfficeAddress().getZipCode() : null, Comparator.nullsFirst(Comparator.reverseOrder()))
      .thenComparing(c -> c.getOfficeAddress() != null ? c.getOfficeAddress().getStreet() : null, Comparator.nullsLast(Comparator.naturalOrder()))
      .thenComparing(c -> c.getOfficeAddress() != null ? c.getOfficeAddress().getBuilding() : null, Comparator.nullsLast(Comparator.naturalOrder()))
      .thenComparing(c -> c.getOfficeAddress() != null ? c.getOfficeAddress().getApartment() : null, Comparator.nullsLast(Comparator.naturalOrder()));
}
