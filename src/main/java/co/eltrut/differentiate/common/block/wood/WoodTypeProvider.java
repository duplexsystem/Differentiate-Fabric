package co.eltrut.differentiate.common.block.wood;

import net.minecraft.block.SignBlock;
import net.minecraft.block.WallSignBlock;
import net.minecraft.entity.vehicle.BoatEntity;

public interface WoodTypeProvider {
    WoodTypeProvider emptyProvider = new WoodTypeProvider() {
        @Override
        public WoodPillarBlock getStrippedLog() {
            return null;
        }

        @Override
        public WoodPillarBlock getStrippedWood() {
            return null;
        }

        @Override
        public WoodPillarBlock getLog() {
            return null;
        }

        @Override
        public WoodPillarBlock getWood() {
            return null;
        }

        @Override
        public WoodPlanksBlock getPlanks() {
            return null;
        }

        @Override
        public WoodSlabBlock getSlab() {
            return null;
        }

        @Override
        public WoodStairsBlock getStairs() {
            return null;
        }

        @Override
        public WoodFenceBlock getFence() {
            return null;
        }

        @Override
        public WoodFenceGateBlock getFenceGate() {
            return null;
        }

        @Override
        public WoodDoorBlock getDoor() {
            return null;
        }

        @Override
        public WoodButtonBlock getButton() {
            return null;
        }

        @Override
        public WoodPressurePlateBlock getPressurePlate() {
            return null;
        }

        @Override
        public SignBlock getSign() {
            return null;
        }

        @Override
        public WallSignBlock getWallSign() {
            return null;
        }

        @Override
        public WoodSignItem getSignItem() {
            return null;
        }

        @Override
        public WoodTrapdoorBlock getTrapdoor() {
            return null;
        }

        @Override
        public WoodBoatItem getBoat() {
            return null;
        }

        @Override
        public BoatEntity.Type getBoatType() {
            return null;
        }

        @Override
        public LogSlabBlock getStrippedWoodSlab() {
            return null;
        }

        @Override
        public LogStairsBlock getStrippedWoodStairs() {
            return null;
        }

        @Override
        public LogWallBlock getStrippedWoodWall() {
            return null;
        }

        @Override
        public LogSlabBlock getWoodSlab() {
            return null;
        }

        @Override
        public LogStairsBlock getWoodStairs() {
            return null;
        }

        @Override
        public LogWallBlock getWoodWall() {
            return null;
        }
    };

    WoodPillarBlock getStrippedLog();

    WoodPillarBlock getStrippedWood();

    WoodPillarBlock getLog();

    WoodPillarBlock getWood();

    WoodPlanksBlock getPlanks();

    WoodSlabBlock getSlab();

    WoodStairsBlock getStairs();

    WoodFenceBlock getFence();

    WoodFenceGateBlock getFenceGate();

    WoodDoorBlock getDoor();

    WoodButtonBlock getButton();

    WoodPressurePlateBlock getPressurePlate();

    SignBlock getSign();

    WallSignBlock getWallSign();

    WoodSignItem getSignItem();

    WoodTrapdoorBlock getTrapdoor();

    WoodBoatItem getBoat();

    BoatEntity.Type getBoatType();

    LogSlabBlock getStrippedWoodSlab();

    LogStairsBlock getStrippedWoodStairs();

    LogWallBlock getStrippedWoodWall();

    LogSlabBlock getWoodSlab();

    LogStairsBlock getWoodStairs();

    LogWallBlock getWoodWall();

    default void applyProvier(WoodType type) {
        WoodPillarBlock providedStrippedLog = this.getStrippedLog();
        type.strippedLog = providedStrippedLog == null ? type.strippedLog : providedStrippedLog;

        WoodPillarBlock providedStrippedWood = this.getStrippedWood();
        type.strippedWood = providedStrippedWood == null ? type.strippedWood : providedStrippedWood;

        WoodPillarBlock providedLog = this.getLog();
        type.log = providedLog == null ? type.log : providedLog;

        WoodPillarBlock providedWood = this.getWood();
        type.wood = providedWood == null ? type.wood : providedWood;

        WoodPlanksBlock providedPlanks = this.getPlanks();
        type.planks = providedPlanks == null ? type.planks : providedPlanks;

        WoodSlabBlock providedSlab = this.getSlab();
        type.slab = providedSlab == null ? type.slab : providedSlab;

        WoodStairsBlock providedStairs = this.getStairs();
        type.stairs = providedStairs == null ? type.stairs : providedStairs;

        WoodFenceBlock providedFence = this.getFence();
        type.fence = providedFence == null ? type.fence : providedFence;

        WoodFenceGateBlock providedFenceGate = this.getFenceGate();
        type.fenceGate = providedFenceGate == null ? type.fenceGate : providedFenceGate;

        WoodDoorBlock providedDoor = this.getDoor();
        type.door = providedDoor == null ? type.door : providedDoor;

        WoodButtonBlock providedButton = this.getButton();
        type.button = providedButton == null ? type.button : providedButton;

        WoodPressurePlateBlock providedPressurePlate = this.getPressurePlate();
        type.pressurePlate = providedPressurePlate == null ? type.pressurePlate : providedPressurePlate;

        SignBlock providedSign = this.getSign();
        type.sign = providedSign == null ? type.sign : providedSign;

        WallSignBlock providedWallSign = this.getWallSign();
        type.wallSign = providedWallSign == null ? type.wallSign : providedWallSign;

        WoodSignItem providedSignItem = this.getSignItem();
        type.signItem = providedSignItem == null ? type.signItem : providedSignItem;

        WoodTrapdoorBlock providedTrapdoor = this.getTrapdoor();
        type.trapdoor = providedTrapdoor == null ? type.trapdoor : providedTrapdoor;

        WoodBoatItem providedBoat = this.getBoat();
        type.boat = providedBoat == null ? type.boat : providedBoat;

        BoatEntity.Type providedBoatType = this.getBoatType();
        type.boatType = providedBoatType == null ? type.boatType : providedBoatType;

        LogSlabBlock providedStrippedWoodSlab = this.getStrippedWoodSlab();
        type.strippedWoodSlab = providedStrippedWoodSlab == null ? type.strippedWoodSlab : providedStrippedWoodSlab;

        LogStairsBlock providedStrippedWoodStairs = this.getStrippedWoodStairs();
        type.strippedWoodStairs = providedStrippedWoodStairs == null ? type.strippedWoodStairs : providedStrippedWoodStairs;

        LogWallBlock providedStrippedWoodWall = this.getStrippedWoodWall();
        type.strippedWoodWall = providedStrippedWoodWall == null ? type.strippedWoodWall : providedStrippedWoodWall;

        LogSlabBlock providedWoodSlab = this.getWoodSlab();
        type.woodSlab = providedWoodSlab == null ? type.woodSlab : providedWoodSlab;

        LogStairsBlock providedWoodStairs = this.getWoodStairs();
        type.woodStairs = providedWoodStairs == null ? type.woodStairs : providedWoodStairs;

        LogWallBlock providedWoodWall = this.getWoodWall();
        type.woodWall = providedWoodWall == null ? type.woodWall : providedWoodWall;
    }
}
