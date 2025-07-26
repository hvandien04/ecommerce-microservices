import React from 'react';

export default function Input({ className = '', ...props }) {
  return (
    <input
      type="text"
      className={`px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 placeholder-gray-400 text-black text-sm ${className}`}
      {...props}
    />
  );
}
