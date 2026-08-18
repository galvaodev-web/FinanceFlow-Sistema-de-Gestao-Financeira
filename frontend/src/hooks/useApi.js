import { useCallback, useEffect, useState } from "react";
export function useApi(loader, dependencies = []) {
  const [data, setData] = useState(null),
    [loading, setLoading] = useState(true),
    [error, setError] = useState("");
  const load = useCallback(async () => {
    setLoading(true);
    setError("");
    try {
      setData(await loader());
    } catch (e) {
      setError(e.message);
    } finally {
      setLoading(false);
    }
  }, dependencies);
  useEffect(() => {
    load();
  }, [load]);
  return { data, loading, error, reload: load };
}
