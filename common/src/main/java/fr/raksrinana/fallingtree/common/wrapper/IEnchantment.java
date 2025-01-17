package fr.raksrinana.fallingtree.common.wrapper;

import fr.raksrinana.fallingtree.common.config.enums.BreakMode;
import org.jetbrains.annotations.NotNull;
import java.util.Optional;

public interface IEnchantment extends IWrapper{
	@NotNull
	Optional<BreakMode> getBreakMode();
}
