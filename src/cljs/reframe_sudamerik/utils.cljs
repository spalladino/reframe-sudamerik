(ns reframe-sudamerik.utils)

(defn total-price
  [{ price :price}, amount]
  (* amount price))

(defn total-amount
  [{ unit-qty :unit-qty}, amount]
  (* amount (or unit-qty 1)))
