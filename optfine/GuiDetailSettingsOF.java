package optfine;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiOptionSlider;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiVideoSettings;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

public class GuiDetailSettingsOF extends GuiScreen
{
    private GuiScreen prevScreen;
    protected String title = "Detail Settings";
    private GameSettings settings;
    private static GameSettings.Options[] enumOptions = new GameSettings.Options[] {GameSettings.Options.CLOUDS, GameSettings.Options.CLOUD_HEIGHT, GameSettings.Options.TREES, GameSettings.Options.RAIN, GameSettings.Options.SKY, GameSettings.Options.STARS, GameSettings.Options.SUN_MOON, GameSettings.Options.SHOW_CAPES, GameSettings.Options.TRANSLUCENT_BLOCKS, GameSettings.Options.HELD_ITEM_TOOLTIPS, GameSettings.Options.DROPPED_ITEMS, GameSettings.Options.ENTITY_SHADOWS, GameSettings.Options.VIGNETTE};
    private int lastMouseX = 0;
    private int lastMouseY = 0;
    private long mouseStillTime = 0L;

    public GuiDetailSettingsOF(GuiScreen p_i35_1_, GameSettings p_i35_2_)
    {
        this.prevScreen = p_i35_1_;
        this.settings = p_i35_2_;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
        int i = 0;

        for (GameSettings.Options gamesettings$options : enumOptions)
        {
            int j = this.width / 2 - 155 + i % 2 * 160;
            int k = this.height / 6 + 21 * (i / 2) - 10;

            if (!gamesettings$options.getEnumFloat())
            {
                this.buttonList.add(new GuiOptionButton(gamesettings$options.returnEnumOrdinal(), j, k, gamesettings$options, this.settings.getKeyBinding(gamesettings$options)));
            }
            else
            {
                this.buttonList.add(new GuiOptionSlider(gamesettings$options.returnEnumOrdinal(), j, k, gamesettings$options));
            }

            ++i;
        }

        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168 + 11, I18n.format("gui.done", new Object[0])));
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button)
    {
        if (button.enabled)
        {
            if (button.id < 200 && button instanceof GuiOptionButton)
            {
                this.settings.setOptionValue(((GuiOptionButton)button).returnEnumOptions(), 1);
                button.displayString = this.settings.getKeyBinding(GameSettings.Options.getEnumOptions(button.id));
            }

            if (button.id == 200)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.prevScreen);
            }

            if (button.id != GameSettings.Options.CLOUD_HEIGHT.ordinal())
            {
                ScaledResolution scaledresolution = new ScaledResolution(this.mc);
                int i = scaledresolution.getScaledWidth();
                int j = scaledresolution.getScaledHeight();
                this.setWorldAndResolution(this.mc, i, j);
            }
        }
    }

    /**
     * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 20, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);

        if (Math.abs(mouseX - this.lastMouseX) <= 5 && Math.abs(mouseY - this.lastMouseY) <= 5)
        {
            int i = 700;

            if (System.currentTimeMillis() >= this.mouseStillTime + (long)i)
            {
                int j = this.width / 2 - 150;
                int k = this.height / 6 - 5;

                if (mouseY <= k + 98)
                {
                    k += 105;
                }

                int l = j + 150 + 150;
                int i1 = k + 84 + 10;
                GuiButton guibutton = this.getSelectedButton(mouseX, mouseY);

                if (guibutton != null)
                {
                    String s = this.getButtonName(guibutton.displayString);
                    String[] astring = this.getTooltipLines(s);

                    if (astring == null)
                    {
                        return;
                    }

                    this.drawGradientRect(j, k, l, i1, -536870912, -536870912);

                    for (int j1 = 0; j1 < astring.length; ++j1)
                    {
                        String s1 = astring[j1];
                        this.fontRendererObj.drawStringWithShadow(s1, (float)(j + 5), (float)(k + 5 + j1 * 11), 14540253);
                    }
                }
            }
        }
        else
        {
            this.lastMouseX = mouseX;
            this.lastMouseY = mouseY;
            this.mouseStillTime = System.currentTimeMillis();
        }
    }

    private String[] getTooltipLines(String p_getTooltipLines_1_)
    {
        return p_getTooltipLines_1_.equals("Clouds") ? new String[] {"Clouds", "  Default - as set by setting Graphics", "  Fast - lower quality, faster", "  Fancy - higher quality, slower", "  OFF - no clouds, fastest", "Fast clouds are rendered 2D.", "Fancy clouds are rendered 3D."}: (p_getTooltipLines_1_.equals("Cloud Height") ? new String[] {"Cloud Height", "  OFF - default height", "  100% - above world height limit"}: (p_getTooltipLines_1_.equals("Trees") ? new String[] {"Trees", "  Default - as set by setting Graphics", "  Fast - lower quality, faster", "  Fancy - higher quality, slower", "Fast trees have opaque leaves.", "Fancy trees have transparent leaves."}: (p_getTooltipLines_1_.equals("Grass") ? new String[] {"Grass", "  Default - as set by setting Graphics", "  Fast - lower quality, faster", "  Fancy - higher quality, slower", "Fast grass uses default side texture.", "Fancy grass uses biome side texture."}: (p_getTooltipLines_1_.equals("Dropped Items") ? new String[] {"Dropped Items", "  Default - as set by setting Graphics", "  Fast - 2D dropped items, faster", "  Fancy - 3D dropped items, slower"}: (p_getTooltipLines_1_.equals("Water") ? new String[] {"Water", "  Default - as set by setting Graphics", "  Fast  - lower quality, faster", "  Fancy - higher quality, slower", "Fast water (1 pass) has some visual artifacts", "Fancy water (2 pass) has no visual artifacts"}: (p_getTooltipLines_1_.equals("Rain & Snow") ? new String[] {"Rain & Snow", "  Default - as set by setting Graphics", "  Fast  - light rain/snow, faster", "  Fancy - heavy rain/snow, slower", "  OFF - no rain/snow, fastest", "When rain is OFF the splashes and rain sounds", "are still active."}: (p_getTooltipLines_1_.equals("Sky") ? new String[] {"Sky", "  ON - sky is visible, slower", "  OFF  - sky is not visible, faster", "When sky is OFF the moon and sun are still visible."}: (p_getTooltipLines_1_.equals("Sun & Moon") ? new String[] {"Sun & Moon", "  ON - sun and moon are visible (default)", "  OFF  - sun and moon are not visible (faster)"}: (p_getTooltipLines_1_.equals("Stars") ? new String[] {"Stars", "  ON - stars are visible, slower", "  OFF  - stars are not visible, faster"}: (p_getTooltipLines_1_.equals("Depth Fog") ? new String[] {"Depth Fog", "  ON - fog moves closer at bedrock levels (default)", "  OFF - same fog at all levels"}: (p_getTooltipLines_1_.equals("Show Capes") ? new String[] {"Show Capes", "  ON - show player capes (default)", "  OFF - do not show player capes"}: (p_getTooltipLines_1_.equals("Held Item Tooltips") ? new String[] {"Held item tooltips", "  ON - show tooltips for held items (default)", "  OFF - do not show tooltips for held items"}: (p_getTooltipLines_1_.equals("Translucent Blocks") ? new String[] {"Translucent Blocks", "  Fancy - correct color blending (default)", "  Fast - fast color blending (faster)", "Controls the color blending of translucent blocks", "with different color (stained glass, water, ice)", "when placed behind each other with air between them."}: (p_getTooltipLines_1_.equals("Vignette") ? new String[] {"Visual effect which slightly darkens the screen corners", "  Default - as set by the setting Graphics (default)", "  Fast - vignette disabled (faster)", "  Fancy - vignette enabled (slower)", "The vignette may have a significant effect on the FPS,", "especially when playing fullscreen.", "The vignette effect is very subtle and can safely", "be disabled"}: null))))))))))))));
    }

    private String getButtonName(String p_getButtonName_1_)
    {
        int i = p_getButtonName_1_.indexOf(58);
        return i < 0 ? p_getButtonName_1_ : p_getButtonName_1_.substring(0, i);
    }

    private GuiButton getSelectedButton(int p_getSelectedButton_1_, int p_getSelectedButton_2_)
    {
        for (int i = 0; i < this.buttonList.size(); ++i)
        {
            GuiButton guibutton = (GuiButton)this.buttonList.get(i);
            int j = GuiVideoSettings.getButtonWidth(guibutton);
            int k = GuiVideoSettings.getButtonHeight(guibutton);
            boolean flag = p_getSelectedButton_1_ >= guibutton.xPosition && p_getSelectedButton_2_ >= guibutton.yPosition && p_getSelectedButton_1_ < guibutton.xPosition + j && p_getSelectedButton_2_ < guibutton.yPosition + k;

            if (flag)
            {
                return guibutton;
            }
        }

        return null;
    }
}
