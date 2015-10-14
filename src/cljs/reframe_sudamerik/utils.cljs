(ns reframe-sudamerik.utils)

(defn total-price
  [{ price :price}, amount]
  (* amount price))

(defn total-amount
  [{ unitqty :unitqty}, amount]
  (* amount (or unitqty 1)))
