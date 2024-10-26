package edu.epam.fop.lambdas.kamino;

import edu.epam.fop.lambdas.kamino.equipment.Equipment;
import java.util.Iterator;

public class BatchPolicies {

  public interface BatchPolicy {

    CloneTrooper[] formBatchOf(CloneTrooper base, int count);
  }

  public static BatchPolicy getCodeAwarePolicy(String codePrefix, int seed) {
    return new BatchPolicy() {
        @Override
        public CloneTrooper[] formBatchOf(CloneTrooper base, int count) {
            CloneTrooper[] batch = new CloneTrooper[count];
            for (int i = 0; i < count; i++) {
                // Generate the new code
                String newCode = String.format("%s-%04d", codePrefix, seed + i);
                // Create a new CloneTrooper with the new code
                CloneTrooper newClone = new CloneTrooper(newCode);
                // Copy other fields from the base clone
                newClone.setNickname(base.getNickname());
                newClone.setEquipment(base.getEquipment()); // Assuming a shallow copy is sufficient here
                batch[i] = newClone;
            }
            return batch;
        }
    };
}



public static BatchPolicy addNicknameAwareness(Iterator<String> nicknames, BatchPolicy policy) {
  return new BatchPolicy() {
      @Override
      public CloneTrooper[] formBatchOf(CloneTrooper base, int count) {
          CloneTrooper[] batch = policy.formBatchOf(base, count);
          for (int i = 0; i < batch.length; i++) {
              if (nicknames.hasNext()) {
                  batch[i].setNickname(nicknames.next());
              } else {
                  batch[i].setNickname(null);
              }
          }
          return batch;
      }
  };
}


public static BatchPolicy addEquipmentOrdering(Equipment equipment, BatchPolicy policy) {
  return new BatchPolicy() {
      @Override
      public CloneTrooper[] formBatchOf(CloneTrooper base, int count) {
          CloneTrooper[] batch = policy.formBatchOf(base, count);
          for (CloneTrooper trooper : batch) {
              Equipment newEquipment = new Equipment();
              newEquipment.setArmor(equipment.getArmor());
              newEquipment.setWeapon(equipment.getWeapon());
              trooper.setEquipment(newEquipment);
          }
          return batch;
      }
  };
}

}
