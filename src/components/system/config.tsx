import  { useState } from 'react';
import { AlertCircle } from 'lucide-react';
import { Alert, AlertTitle, AlertDescription } from '../../components/ui/alert';
import { Card, CardHeader, CardTitle, CardContent } from '../../components/ui/card';

const ConfigurationForm = () => {
  const [config, setConfig] = useState({
    totalTickets: 0,
    ticketReleaseRate: 0,
    customerRetrievalRate: 0,
    maxTicketCapacity: 0
  });
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  const handleInputChange = (e:any) => {
    const { name, value } = e.target;
    setConfig(prev => ({
      ...prev,
      [name]: parseInt(value) || 0
    }));
  };

  const handleSubmit = async (e:any) => {
    e.preventDefault();
    
    // Validation
    if (Object.values(config).some(value => value <= 0)) {
      setError('All values must be positive numbers');
      return;
    }

    try {
      const response = await fetch('http://localhost:8080/api/config', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(config),
      });

      if (response.ok) {
        setSuccess('Configuration saved successfully!');
        setError('');
      } else {
        setError('Failed to save configuration');
        setSuccess('');
      }
    } catch (err) {
      setError('Error connecting to server');
      setSuccess('');
    }
  };

  return (
    <div className="max-w-4xl mx-auto p-4">
      <Card>
        <CardHeader>
          <CardTitle>Ticket System Configuration</CardTitle>
        </CardHeader>
        <CardContent>
          <form onSubmit={handleSubmit} className="space-y-4">
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              <div className="space-y-2">
                <label className="block text-sm font-medium">
                  Total Number of Tickets
                </label>
                <input
                  type="number"
                  name="totalTickets"
                  value={config.totalTickets}
                  onChange={handleInputChange}
                  className="w-full p-2 border rounded"
                  min="1"
                />
              </div>

              <div className="space-y-2">
                <label className="block text-sm font-medium">
                  Ticket Release Rate
                </label>
                <input
                  type="number"
                  name="ticketReleaseRate"
                  value={config.ticketReleaseRate}
                  onChange={handleInputChange}
                  className="w-full p-2 border rounded"
                  min="1"
                />
              </div>

              <div className="space-y-2">
                <label className="block text-sm font-medium">
                  Customer Retrieval Rate
                </label>
                <input
                  type="number"
                  name="customerRetrievalRate"
                  value={config.customerRetrievalRate}
                  onChange={handleInputChange}
                  className="w-full p-2 border rounded"
                  min="1"
                />
              </div>

              <div className="space-y-2">
                <label className="block text-sm font-medium">
                  Maximum Ticket Capacity
                </label>
                <input
                  type="number"
                  name="maxTicketCapacity"
                  value={config.maxTicketCapacity}
                  onChange={handleInputChange}
                  className="w-full p-2 border rounded"
                  min="1"
                />
              </div>
            </div>

            {error && (
              <Alert variant="destructive">
                <AlertCircle className="h-4 w-4" />
                <AlertTitle>Error</AlertTitle>
                <AlertDescription>{error}</AlertDescription>
              </Alert>
            )}

            {success && (
              <Alert className="bg-green-50 border-green-200">
                <AlertTitle>Success</AlertTitle>
                <AlertDescription>{success}</AlertDescription>
              </Alert>
            )}

            <button
              type="submit"
              className="w-full bg-blue-500 text-white p-2 rounded hover:bg-blue-600 transition-colors"
            >
              Save Configuration
            </button>
          </form>
        </CardContent>
      </Card>
    </div>
  );
};

export default ConfigurationForm;