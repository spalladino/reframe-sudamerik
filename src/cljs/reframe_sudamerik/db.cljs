(ns reframe-sudamerik.db)

(def default-db
  { :name "re-frame",
    :products [
      { :group "Frutas secas", :name "Mix de frutas secas", :id "302", :price 89.90, :unit "kg" },
      { :group "Frutas secas", :name "Avellanas peladas", :id "11", :price 279.90, :unit "kg" },
      { :group "Especias", :name "Curcuma", :id "129", :price 15.90, :unit "kg" },
      { :group "Especias", :name "Aji molido", :id "29", :price 25.90, :unit "kg" },
      { :group "Otros", :name "Aceite de Oliva", :id "58", :price 22.90, :unit "ml", :unit-qty 500 }
    ],
    :requests []})
