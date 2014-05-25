cat << EOF
common: &default_settings
  license_key: '$NEWRELIC_LICENSE'
  enable_auto_transaction_naming: false
  app_name: $NEWRELIC_APPNAME
EOF