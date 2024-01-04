package spireMapOverhaul.zones.manasurge.modifiers.common.negative;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.FrailPower;
import spireMapOverhaul.util.TexLoader;
import spireMapOverhaul.zones.manasurge.interfaces.ModifierTags;
import spireMapOverhaul.zones.manasurge.utils.ManaSurgeTags;

import java.util.ArrayList;
import java.util.List;

import static spireMapOverhaul.SpireAnniversary6Mod.makeImagePath;

public class FeebleModifier extends AbstractCardModifier implements ModifierTags {
    private static final Texture ICON = TexLoader.getTexture(makeImagePath("ui/extraIcons/NegativeEnchantmentIcon.png"));
    private static final int FRAIL_AMT = 1;

    @Override
    public boolean isPositiveModifier() {
        return false;
    }

    @Override
    public boolean isCommonModifier() {
        return true;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FrailPower(AbstractDungeon.player,FRAIL_AMT,false),FRAIL_AMT));
    }

    @Override
    public List<TooltipInfo> additionalTooltips(AbstractCard card) {
        List<TooltipInfo> tooltips = new ArrayList<>();
        tooltips.add(new TooltipInfo("[#ff8cd5]Feeble[]", "Gain #b1 #yFrail."));
        return tooltips;
    }

    @Override
    public boolean removeAtEndOfTurn(AbstractCard card) {
        return !card.retain || !card.hasTag(ManaSurgeTags.PERMANENT_MODIFIER);
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return "[#ff8cd5]Feeble[]." + " NL " + rawDescription;
    }

    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {
        ExtraIcons.icon(ICON).render(card);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new FeebleModifier();
    }
}