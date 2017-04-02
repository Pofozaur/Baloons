/**
 * Created by kamil on 02.04.2017.
 */
public class BaloonManager {

    private BaloonsList showed;
    private BaloonsList hidden;

    private int visibleRows;
    private int allRows;
    private int columns;
    int mapRows;

    private BaloonDrawer baloonDrawer;

    BaloonManager(){
        baloonDrawer = new BaloonDrawer();
    }

    public void load(String fileName){
        BaloonFileReader bfl = new BaloonFileReader(fileName);
        bfl.load();
        mapRows = bfl.getMapRows();
        columns = bfl.getColumns();
        allRows = bfl.getAllRows();
        visibleRows = bfl.getVisibleRows();
        showed = bfl.getVisibleBaloons();
        hidden = bfl.getHiddenBaloons();
        System.out.println("LOAD " + fileName);
    }

    public BaloonDrawer getBaloonDrawer() {
        return baloonDrawer;
    }

    public BaloonDrawer prepareBaloonDrawer(){
        baloonDrawer.setColumns(columns);
        if(showed != null)
        for(BaloonRow br : showed)
            for(Baloon b : br)
                baloonDrawer.add(b);
        if(showed != null)
        for(BaloonRow br : showed)
            for(Baloon b : br)
                System.out.println(b);
        return baloonDrawer;
    }

    public void loadNext(String fileName){
        load(fileName);
        System.out.println("LOADNEXT");
        resetBaloonDrawer();
    }
    private void resetBaloonDrawer(){
        baloonDrawer.removeAll();
        System.out.println("resetBD");
        prepareBaloonDrawer();
    }

}
