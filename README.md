**Example Code**

```java
@Override
public void onEnable() {
    MessageAPI.setup(this);

    new BukkitRunnable() {
        @Override
        public void run() {
            MessageAPI.getTitleAPI().getTitleManager().setTitle("sa").setSubtitle("as").setStay(20).create().sendAll();
            MessageAPI.getActionBarAPI().getActionBarManager().setText("sa").create().sendAll();
            MessageAPI.getBarAPI().getBarManager().create().addPlayer(Bukkit.getPlayer("blueybighats"));
        }
    }.runTaskLater(this, 20 * 5);
}
```
