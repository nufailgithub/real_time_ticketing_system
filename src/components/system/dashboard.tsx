import { useState } from "react";
import { Button } from "../ui/button";
import { Card, CardHeader, CardTitle, CardContent } from "../ui/card";
import { ScrollArea } from "../ui/scroll-area";

const Dashboard = () => {
  const [logs, setLogs] = useState<string[]>([]);
  const [vendorReleaseCount, setVendorReleaseCount] = useState<number>(0);
  const [customerPurchaseCount, setCustomerPurchaseCount] = useState<number>(0);
  const [ticketPool, setTicketPool] = useState<{ current: number; max: number }>(
    { current: 0, max: 1000 }
  );

  const handleStart = () => {
    setLogs((prevLogs) => ["✅ Start button clicked", ...prevLogs]);
    // Simulate ticket release (increment by 10 every time Start is clicked)
    setVendorReleaseCount((prev) => prev + 10);
    setTicketPool((prev) => ({
      ...prev,
      current: prev.current + 10 > prev.max ? prev.max : prev.current + 10,
    }));
  };

  const handleStop = () => {
    setLogs((prevLogs) => ["🛑 Stop button clicked", ...prevLogs]);
  };

  const handleCustomerPurchase = () => {
    // Simulate customer ticket purchase (decrement by 5)
    setCustomerPurchaseCount((prev) => prev + 5);
    setTicketPool((prev) => ({
      ...prev,
      current: prev.current - 5 < 0 ? 0 : prev.current - 5,
    }));
    setLogs((prevLogs) => ["🎟️ Customer purchased 5 tickets", ...prevLogs]);
  };

  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-gradient-to-r from-indigo-50 via-blue-50 to-indigo-100 p-6">
      <Card className="w-full max-w-4xl shadow-lg">
        <CardHeader className="text-center">
          <CardTitle className="text-4xl font-bold text-indigo-600 mb-2">
            Ticket Bulking System Dashboard
          </CardTitle>
          <p className="text-lg text-gray-500">Real-time ticket management</p>
        </CardHeader>
        <CardContent>
          <div className="flex justify-between items-center mt-6">
            <div className="text-center">
              <h3 className="text-lg font-semibold text-gray-700">Vendor Release Count</h3>
              <p className="text-3xl font-bold text-green-600">{vendorReleaseCount}</p>
            </div>
            <div className="text-center">
              <h3 className="text-lg font-semibold text-gray-700">Customer Purchases</h3>
              <p className="text-3xl font-bold text-blue-600">{customerPurchaseCount}</p>
            </div>
            <div className="text-center">
              <h3 className="text-lg font-semibold text-gray-700">Ticket Pool</h3>
              <p className="text-3xl font-bold text-purple-600">
                {ticketPool.current} / {ticketPool.max}
              </p>
            </div>
          </div>
          <div className="flex justify-center space-x-6 mt-6">
            <Button
              onClick={handleStart}
              className="px-6 py-3 bg-gradient-to-r from-green-400 via-green-500 to-green-600 text-white rounded-lg shadow-lg hover:shadow-xl hover:scale-105 transition-transform"
            >
              🚀 Start
            </Button>
            <Button
              onClick={handleStop}
              className="px-6 py-3 bg-gradient-to-r from-red-400 via-red-500 to-red-600 text-white rounded-lg shadow-lg hover:shadow-xl hover:scale-105 transition-transform"
            >
              🛑 Stop
            </Button>
            <Button
              onClick={handleCustomerPurchase}
              className="px-6 py-3 bg-gradient-to-r from-blue-400 via-blue-500 to-blue-600 text-white rounded-lg shadow-lg hover:shadow-xl hover:scale-105 transition-transform"
            >
              🎟️ Buy Tickets
            </Button>
          </div>
          <div className="mt-8">
            <h2 className="text-2xl font-semibold text-gray-800 mb-4">Logs</h2>
            <ScrollArea className="h-64 bg-gray-50 border rounded-lg shadow-inner p-4">
              {logs.length > 0 ? (
                <ul className="space-y-2">
                  {logs.map((log, index) => (
                    <li
                      key={index}
                      className="p-2 bg-indigo-100 rounded-lg shadow-sm text-gray-800"
                    >
                      {log}
                    </li>
                  ))}
                </ul>
              ) : (
                <p className="text-gray-500 text-center">
                  No logs available yet.
                </p>
              )}
            </ScrollArea>
          </div>
        </CardContent>
      </Card>
    </div>
  );
};

export default Dashboard;
