(ns subvec-rev.core
  (:import (subvec SubvecRev)))

(defn reverse-diap [vec from to]
  (SubvecRev. nil vec from to))

(defn -main [& args]
  (prn (reverse-diap [1 2 3 4] 1 3)))
