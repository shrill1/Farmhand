package net.shrill.farmhand.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SugarCaneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BambooLeaves;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.common.PlantType;
import net.shrill.farmhand.item.FHItems;
import org.apache.http.impl.cookie.BasicMaxAgeHandler;

public class CaneCornCrop extends SugarCaneBlock {

    public static final IntegerProperty POSITION = IntegerProperty.create("position",0,2);

    public CaneCornCrop(Properties properties) {
        super(properties);
    }


    public void itGrew(BlockState blockState, LevelReader levelReader, BlockPos blockPos, IntegerProperty intPos, Level pLevel){
        if(canSurvive(blockState, levelReader,blockPos)){
            pLevel.setBlock(blockPos,blockState.setValue(POSITION,1),3);
        }
    }


    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        BlockState soil = levelReader.getBlockState(blockPos.below());
        if (soil.canSustainPlant(levelReader, blockPos.below(), Direction.UP, this)){

            return true;
        }
        BlockState blockstate = levelReader.getBlockState(blockPos.below());
        if (blockstate.is(this)) {
            return true;
        } else {
            if (blockstate.is(Blocks.FARMLAND)) {
                BlockPos blockpos = blockPos.below();
            }
            return false;
        }
    }

    @Override
    public net.minecraftforge.common.PlantType getPlantType(BlockGetter world, BlockPos pos) {
        return net.minecraftforge.common.PlantType.CROP;
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder){
        pBuilder.add(POSITION);
    }



}
