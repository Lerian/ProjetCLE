package interfaces;

import java.util.List;

public interface IPluginManager {
	public IPlugin loadPlugin(String pluginName, /*List<*/String/*>*/ initPath);
	public IPlugin loadRandomPlugin(String pluginType);
}
