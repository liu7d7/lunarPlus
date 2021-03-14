package net.minecraft.client.gui;

import java.io.IOException;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import optfine.GuiAnimationSettingsOF;
import optfine.GuiDetailSettingsOF;
import optfine.GuiOtherSettingsOF;
import optfine.GuiPerformanceSettingsOF;
import optfine.GuiQualitySettingsOF;

public class GuiVideoSettings extends GuiScreen
{
    private GuiScreen parentGuiScreen;
    protected String screenTitle = "Video Settings";
    private GameSettings guiGameSettings;
    private boolean is64bit;

    /** An array of all of GameSettings.Options's video options. */
    private static GameSettings.Options[] videoOptions = new GameSettings.Options[] {GameSettings.Options.GRAPHICS, GameSettings.Options.RENDER_DISTANCE, GameSettings.Options.AMBIENT_OCCLUSION, GameSettings.Options.FRAMERATE_LIMIT, GameSettings.Options.AO_LEVEL, GameSettings.Options.VIEW_BOBBING, GameSettings.Options.GUI_SCALE, GameSettings.Options.USE_VBO, GameSettings.Options.GAMMA, GameSettings.Options.BLOCK_ALTERNATIVES, GameSettings.Options.FOG_FANCY, GameSettings.Options.FOG_START, GameSettings.Options.ANAGLYPH};
    private static final String __OBFID = "CL_00000718";
    private int lastMouseX = 0;
    private int lastMouseY = 0;
    private long mouseStillTime = 0L;

    public GuiVideoSettings(GuiScreen parentScreenIn, GameSettings gameSettingsIn)
    {
        this.parentGuiScreen = parentScreenIn;
        this.guiGameSettings = gameSettingsIn;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
        this.screenTitle = I18n.format("options.videoTitle", new Object[0]);
        this.buttonList.clear();
        this.is64bit = false;
        String[] astring = new String[] {"sun.arch.data.model", "com.ibm.vm.bitmode", "os.arch"};

        for (String s : astring)
        {
            String s1 = System.getProperty(s);

            if (s1 != null && s1.contains("64"))
            {
                this.is64bit = true;
                break;
            }
        }

        int l = 0;
        boolean flag = !this.is64bit;
        GameSettings.Options[] agamesettings$options = videoOptions;
        int i1 = agamesettings$options.length;
        int i = 0;

        for (i = 0; i < i1; ++i)
        {
            GameSettings.Options gamesettings$options = agamesettings$options[i];

            if (gamesettings$options != null)
            {
                int j = this.width / 2 - 155 + i % 2 * 160;
                int k = this.height / 6 + 21 * (i / 2) - 10;

                if (gamesettings$options.getEnumFloat())
                {
                    this.buttonList.add(new GuiOptionSlider(gamesettings$options.returnEnumOrdinal(), j, k, gamesettings$options));
                }
                else
                {
                    this.buttonList.add(new GuiOptionButton(gamesettings$options.returnEnumOrdinal(), j, k, gamesettings$options, this.guiGameSettings.getKeyBinding(gamesettings$options)));
                }
            }
        }

        int j1 = this.height / 6 + 21 * (i / 2) - 10;
        int k1 = 0;
        k1 = this.width / 2 - 155 + 160;
        this.buttonList.add(new GuiOptionButton(202, k1, j1, "Quality..."));
        j1 = j1 + 21;
        k1 = this.width / 2 - 155 + 0;
        this.buttonList.add(new GuiOptionButton(201, k1, j1, "Details..."));
        k1 = this.width / 2 - 155 + 160;
        this.buttonList.add(new GuiOptionButton(212, k1, j1, "Performance..."));
        j1 = j1 + 21;
        k1 = this.width / 2 - 155 + 0;
        this.buttonList.add(new GuiOptionButton(211, k1, j1, "Animations..."));
        k1 = this.width / 2 - 155 + 160;
        this.buttonList.add(new GuiOptionButton(222, k1, j1, "Other..."));
        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168 + 11, I18n.format("gui.done", new Object[0])));
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.enabled)
        {
            int i = this.guiGameSettings.guiScale;

            if (button.id < 200 && button instanceof GuiOptionButton)
            {
                this.guiGameSettings.setOptionValue(((GuiOptionButton)button).returnEnumOptions(), 1);
                button.displayString = this.guiGameSettings.getKeyBinding(GameSettings.Options.getEnumOptions(button.id));
            }

            if (button.id == 200)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.parentGuiScreen);
            }

            if (this.guiGameSettings.guiScale != i)
            {
                ScaledResolution scaledresolution = new ScaledResolution(this.mc);
                int j = scaledresolution.getScaledWidth();
                int k = scaledresolution.getScaledHeight();
                this.setWorldAndResolution(this.mc, j, k);
            }

            if (button.id == 201)
            {
                this.mc.gameSettings.saveOptions();
                GuiDetailSettingsOF guidetailsettingsof = new GuiDetailSettingsOF(this, this.guiGameSettings);
                this.mc.displayGuiScreen(guidetailsettingsof);
            }

            if (button.id == 202)
            {
                this.mc.gameSettings.saveOptions();
                GuiQualitySettingsOF guiqualitysettingsof = new GuiQualitySettingsOF(this, this.guiGameSettings);
                this.mc.displayGuiScreen(guiqualitysettingsof);
            }

            if (button.id == 211)
            {
                this.mc.gameSettings.saveOptions();
                GuiAnimationSettingsOF guianimationsettingsof = new GuiAnimationSettingsOF(this, this.guiGameSettings);
                this.mc.displayGuiScreen(guianimationsettingsof);
            }

            if (button.id == 212)
            {
                this.mc.gameSettings.saveOptions();
                GuiPerformanceSettingsOF guiperformancesettingsof = new GuiPerformanceSettingsOF(this, this.guiGameSettings);
                this.mc.displayGuiScreen(guiperformancesettingsof);
            }

            if (button.id == 222)
            {
                this.mc.gameSettings.saveOptions();
                GuiOtherSettingsOF guiothersettingsof = new GuiOtherSettingsOF(this, this.guiGameSettings);
                this.mc.displayGuiScreen(guiothersettingsof);
            }

            if (button.id == GameSettings.Options.AO_LEVEL.ordinal())
            {
                return;
            }
        }
    }

    /**
     * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, this.screenTitle, this.width / 2, this.is64bit ? 20 : 5, 16777215);

        if (!this.is64bit && this.guiGameSettings.renderDistanceChunks > 8)
        {
            ;
        }

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
        return p_getTooltipLines_1_.equals("Graphics") ? new String[] {"Visual quality", "  Fast  - lower quality, faster", "  Fancy - higher quality, slower", "Changes the appearance of clouds, leaves, water,", "shadows and grass sides."}: (p_getTooltipLines_1_.equals("Render Distance") ? new String[] {"Visible distance", "  2 Tiny - 32m (fastest)", "  4 Short - 64m (faster)", "  8 Normal - 128m", "  16 Far - 256m (slower)", "  32 Extreme - 512m (slowest!)", "The Extreme view distance is very resource demanding!", "Values over 16 Far are only effective in local worlds."}: (p_getTooltipLines_1_.equals("Smooth Lighting") ? new String[] {"Smooth lighting", "  OFF - no smooth lighting (faster)", "  Minimum - simple smooth lighting (slower)", "  Maximum - complex smooth lighting (slowest)"}: (p_getTooltipLines_1_.equals("Smooth Lighting Level") ? new String[] {"Smooth lighting level", "  OFF - no shadows", "  50% - light shadows", "  100% - dark shadows"}: (p_getTooltipLines_1_.equals("Max Framerate") ? new String[] {"Max framerate", "  VSync - limit to monitor framerate (60, 30, 20)", "  5-255 - variable", "  Unlimited - no limit (fastest)", "The framerate limit decreases the FPS even if", "the limit value is not reached."}: (p_getTooltipLines_1_.equals("View Bobbing") ? new String[] {"More realistic movement.", "When using mipmaps set it to OFF for best results."}: (p_getTooltipLines_1_.equals("GUI Scale") ? new String[] {"GUI Scale", "Smaller GUI might be faster"}: (p_getTooltipLines_1_.equals("Server Textures") ? new String[] {"Server textures", "Use the resource pack recommended by the server"}: (p_getTooltipLines_1_.equals("Advanced OpenGL") ? new String[] {"Detect and render only visible geometry", "  OFF - all geometry is rendered (slower)", "  Fast - only visible geometry is rendered (fastest)", "  Fancy - conservative, avoids visual artifacts (faster)", "The option is available only if it is supported by the ", "graphic card."}: (p_getTooltipLines_1_.equals("Fog") ? new String[] {"Fog type", "  Fast - faster fog", "  Fancy - slower fog, looks better", "  OFF - no fog, fastest", "The fancy fog is available only if it is supported by the ", "graphic card."}: (p_getTooltipLines_1_.equals("Fog Start") ? new String[] {"Fog start", "  0.2 - the fog starts near the player", "  0.8 - the fog starts far from the player", "This option usually does not affect the performance."}: (p_getTooltipLines_1_.equals("Brightness") ? new String[] {"Increases the brightness of darker objects", "  OFF - standard brightness", "  100% - maximum brightness for darker objects", "This options does not change the brightness of ", "fully black objects"}: (p_getTooltipLines_1_.equals("Chunk Loading") ? new String[] {"Chunk Loading", "  Default - unstable FPS when loading chunks", "  Smooth - stable FPS", "  Multi-Core - stable FPS, 3x faster world loading", "Smooth and Multi-Core remove the stuttering and ", "freezes caused by chunk loading.", "Multi-Core can speed up 3x the world loading and", "increase FPS by using a second CPU core."}: (p_getTooltipLines_1_.equals("Alternate Blocks") ? new String[] {"Alternate Blocks", "Uses alternative block models for some blocks.", "Depends on the selected resource pack."}: (p_getTooltipLines_1_.equals("Use VBOs") ? new String[] {"Vertex Buffer Objects", "Uses an alternative rendering model which is usually", "faster (5-10%) than the default rendering."}: (p_getTooltipLines_1_.equals("3D Anaglyph") ? new String[] {"3D Anaglyph", "Enables a stereoscopic 3D effect using different colors", "for each eye.", "Requires red-cyan glasses for proper viewing."}: null)))))))))))))));
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
            boolean flag = p_getSelectedButton_1_ >= guibutton.xPosition && p_getSelectedButton_2_ >= guibutton.yPosition && p_getSelectedButton_1_ < guibutton.xPosition + guibutton.width && p_getSelectedButton_2_ < guibutton.yPosition + guibutton.height;

            if (flag)
            {
                return guibutton;
            }
        }

        return null;
    }

    public static int getButtonWidth(GuiButton p_getButtonWidth_0_)
    {
        return p_getButtonWidth_0_.width;
    }

    public static int getButtonHeight(GuiButton p_getButtonHeight_0_)
    {
        return p_getButtonHeight_0_.height;
    }
}
