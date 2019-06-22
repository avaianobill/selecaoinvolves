const proxy = [
    {
      context: '/funcionarios',
      target: 'http://localhost:8080'
    }
  ];
  
  module.exports = proxy;