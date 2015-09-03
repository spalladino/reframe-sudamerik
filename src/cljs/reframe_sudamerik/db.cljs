(ns reframe-sudamerik.db)

(def default-db
  { :name "re-frame",
    :products [
      { :name "Mix de frutas secas", :id "302", :price 89.90, :unit "kg" },
      { :name "Curcuma", :id "129", :price 15.90, :unit "kg" },
      { :name "Aji molido", :id "29", :price 25.90, :unit "kg" },
      { :name "Aceite de Oliva", :id "58", :price 22.90, :unit "500ml" }
    ],
    :requests []})
