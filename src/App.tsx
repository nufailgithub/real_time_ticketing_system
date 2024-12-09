import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import ConfigurationForm from "../src/components/system/config";
import Dashboard from "./components/system/dashboard.tsx";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<ConfigurationForm />} />
        <Route path="/dashboard" element={<Dashboard />} />
      </Routes>
    </Router>
  );
}

export default App;
