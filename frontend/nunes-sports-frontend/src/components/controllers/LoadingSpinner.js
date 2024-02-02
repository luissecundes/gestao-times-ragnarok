import React from 'react';
import { RotatingLines } from 'react-loader-spinner';

function LoadingSpinner() {
  return (
    <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
      <RotatingLines color="#de7c04" height={100} width={100} />
    </div>
  );
}

export default LoadingSpinner;
