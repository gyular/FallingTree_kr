package fr.raksrinana.fallingtree.common.tree.builder.position;

import fr.raksrinana.fallingtree.common.FallingTreeCommon;
import fr.raksrinana.fallingtree.common.tree.builder.ToAnalyzePos;
import fr.raksrinana.fallingtree.common.wrapper.IBlockPos;
import fr.raksrinana.fallingtree.common.wrapper.ILevel;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import java.util.Collection;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BasicPositionFetcher implements IPositionFetcher{
	private static BasicPositionFetcher INSTANCE;
	
	@NotNull
	private final FallingTreeCommon<?> mod;
	
	@Override
	@NotNull
	public Collection<ToAnalyzePos> getPositions(@NotNull ILevel level, @NotNull IBlockPos originPos, @NotNull ToAnalyzePos parent){
		var parentPos = parent.checkPos();
		var parentBlock = level.getBlockState(parentPos).getBlock();
		return parentPos.betweenClosedStream(parentPos.above().north().east(), parentPos.below().south().west())
				.map(checkPos -> {
					var checkBlock = level.getBlockState(checkPos).getBlock();
					return new ToAnalyzePos(this, parentPos, parentBlock, checkPos.immutable(), checkBlock, mod.getTreePart(checkBlock), parent.sequence() + 1);
				})
				.collect(toList());
	}
	
	@NotNull
	public static BasicPositionFetcher getInstance(@NotNull FallingTreeCommon<?> common){
		if(isNull(INSTANCE)){
			INSTANCE = new BasicPositionFetcher(common);
		}
		return INSTANCE;
	}
}
