public class Producto implements IProducto {
    
    private Comparable etiqueta;
    private String nombre;
    private float precio;
    private Integer stock;

    public Producto(Comparable etiqueta, String nombre, float precio) {
        this.etiqueta = etiqueta;
        this.nombre = nombre;
        this.precio = precio;
        // Siempre que se agrega un producto es porque se está agregando stock, por lo tanto le defino por defecto stock 1.
        this.stock = 1;
        
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public float getPrecio() {
        return precio;
    }

    @Override
    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    @Override
    public Integer getStock() {
        return stock;
    }

    public void agregarStock(Integer stock) {
        if (stock > 0){
            this.stock += stock;
        }
        else {
            System.out.println("Error. La cantidad a incrementar no puede ser negativa.");
        }     
    }
    
    // restarStock devolverá -1 si se pretende extraer más de lo que hay... 
    // y el campo stock quedará inalterado
    public Integer restarStock(Integer stock) {
        if (stock > this.stock) {
            return -1;
        } else {
            setStock(this.stock - stock);
            return this.stock;
        }
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public Comparable getEtiqueta() {
        return this.etiqueta;
    }

}
