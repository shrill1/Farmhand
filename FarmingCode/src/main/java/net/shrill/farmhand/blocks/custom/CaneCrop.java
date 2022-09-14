package net.shrill.farmhand.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SugarCaneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;

// This class is used for making Crops that can grow multiple blocks high by extending
// the SugarCaneBlock Class and Overriding a few of its Methods
// Author: Shrill Last Updated: 9_13_2022 6:24 PM

// Usage under MIT Licence

public class CaneCrop extends SugarCaneBlock {

    //Variables to change things easily and to help readability
    private static int MAX_AGE = 15;
    private static int MAX_GROWTH = 2;

    // The Growth IntegerProperty is used for mapping textures so that they can be different rather than all the same reed texture
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, MAX_AGE);
    public static final IntegerProperty GROWTH = IntegerProperty.create("growth", 0, MAX_GROWTH);

    public static final BooleanProperty HAS_GROWN = BooleanProperty.create("has_grown");


    // Class Constructor, passes properties to the superclass constructor and registers the default state for the GROWTH property to 0
    public CaneCrop(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(GROWTH, 0));
       // this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
        this.registerDefaultState(this.getStateDefinition().any().setValue(HAS_GROWN, false));
    }


    // setters and getters :P
    public static void setAge(int age) {
        MAX_AGE = age;
    }

    public static void setGROWTH(int growth) {
        MAX_GROWTH = growth;
    }

    public static int getMaxAge() {
        return MAX_AGE;
    }

    public static int getMaxGrowth() {
        return MAX_GROWTH;
    }


    // Overrides the randomTick method to create the next block with an incremented GROWTH property
    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {


        if (pLevel.isEmptyBlock(pPos.above())) { // if there is no block above,
            int i;
            for (i = 1; pLevel.getBlockState(pPos.below(i)).is(this); ++i) { // look at the block below, and while it's the same block, increment i
            }

            if (i < 3) { // if there are less than 3 blocks,
                int j = pState.getValue(AGE); // set j to the age of the block,
                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(pLevel, pPos, pState, true)) {
                    if (j == MAX_AGE) { // if the max age,

                        if (i == 1) { // if the first growth iteration
                            pLevel.setBlockAndUpdate(pPos.above(), this.stateDefinition.any().setValue(GROWTH, 1).setValue(HAS_GROWN,false)); // create new block above, setting Growth to 1
                        } else if (i == 2) {
                            pLevel.setBlockAndUpdate(pPos.above(), this.stateDefinition.any().setValue(GROWTH, 2).setValue(HAS_GROWN, true)); // create new block above, setting Growth to 2

                        }
                        net.minecraftforge.common.ForgeHooks.onCropsGrowPost(pLevel, pPos.above(), this.defaultBlockState()); // getting the state of the current block?
                        pLevel.setBlock(pPos, pState.setValue(AGE, 0), 4); // update the age to 0, so that if broken, a new block isn't immediately grown
                        pLevel.setBlock(pPos, pState.setValue(HAS_GROWN, true), 3);
                    } else {
                        pLevel.setBlock(pPos, pState.setValue(AGE, j + 1), 4); // increment age, until its at the max
                    }
                }
            }
        }
    }


    // Overrides the canSurvive method to allow the crop to grow on Farmland instead of dirt/grass/sands, I also got rid of the
    // for loop that checks for water
    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        BlockState soil = levelReader.getBlockState(blockPos.below()); // get the block below
        if (soil.canSustainPlant(levelReader, blockPos.below(), Direction.UP, this)) { // if that block can sustain the plant, true
            return true;
        }
        BlockState blockstate = levelReader.getBlockState(blockPos.below()); // get the blockstate from below, return true if it is an instance of this object
        if (blockstate.is(this)) {
            return true;
        } else {
            if (blockstate.is(Blocks.FARMLAND)) { // otherwise, if its farmland set the blockpos to farmland
                BlockPos blockpos = blockPos.below();
            }
            return false; // anything else is false
        }
    }

    // Overrides the getPlantType method to specify that it's a CROP type of plant instead of a BEACH type of plant
    @Override
    public net.minecraftforge.common.PlantType getPlantType(BlockGetter world, BlockPos pos) {
        return net.minecraftforge.common.PlantType.CROP;
    }

    // Overrides the createBlockStateDefinition method to add my Growth/Age Property
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
        pBuilder.add(GROWTH);
        pBuilder.add(HAS_GROWN);


    }
}