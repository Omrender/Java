package edu.epam.fop.lambdas.calculator;

import edu.epam.fop.lambdas.insurance.Accommodation;
import edu.epam.fop.lambdas.insurance.Currency;
import java.math.BigInteger;
import java.util.Optional;

public final class AccommodationInsurancePolicies {

  private AccommodationInsurancePolicies() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  static InsuranceCalculator<Accommodation> rentDependentInsurance(BigInteger divider) {
    return accommodation -> {
      if (accommodation == null || !accommodation.rent().isPresent()) {
        return Optional.empty();
      }

      var rent = accommodation.rent().get();
      var currency = rent.currency();
      if (!currency.equals(Currency.USD)) {
        return Optional.empty();
      }

      var amount = rent.amount();
      if (amount.compareTo(BigInteger.ZERO) <= 0) {
        return Optional.empty();
      }

      BigInteger insuranceCoefficient = amount.multiply(BigInteger.valueOf(100)).divide(divider);
      if (insuranceCoefficient.compareTo(BigInteger.valueOf(100)) > 0) {
        return Optional.of(InsuranceCoefficient.MAX);
      }

      return Optional.of(InsuranceCoefficient.of(insuranceCoefficient));
    };
  }

  static InsuranceCalculator<Accommodation> priceAndRoomsAndAreaDependentInsurance(BigInteger priceThreshold,
      int roomsThreshold, BigInteger areaThreshold) {
    return accommodation -> {
      if (accommodation == null) {
        return Optional.empty();
      }

      var price = accommodation.price();
      var rooms = accommodation.rooms();
      var area = accommodation.area();

      if (price == null || price.compareTo(priceThreshold) < 0 ||
          rooms < roomsThreshold ||
          area == null || area.compareTo(areaThreshold) < 0) {
        return Optional.of(InsuranceCoefficient.MIN);
      }

      return Optional.of(InsuranceCoefficient.MAX);
    };
  }
}
