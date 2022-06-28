import React, { useEffect } from 'react';

import { StyleSheet, View, Text, NativeModules } from 'react-native';

export default function App() {
  const [result, setResult] = React.useState<number | undefined>();
  const { InAppPush } = NativeModules;

  useEffect(() => {
    console.log('InAppPush', NativeModules);
    InAppPush.multiply(4, 4)
      .then((rs) => {
        console.log('RS', rs);
      })
      .catch((err) => {
        console.log(err);
      });
    InAppPush.showNotification(
      'Rewards',
      'Deal Expiring Soon',
      'Check before it expires'
    );
  });

  return (
    <View style={styles.container}>
      <Text>Result: {result}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});

/**
 * import React, {useEffect} from 'react';
import {NativeModules, Text} from 'react-native';

const {InAppPush} = NativeModules;

const nativeF = () => {
  // eslint-disable-next-line react-hooks/rules-of-hooks
  useEffect(() => {
    console.log('InAppPush', NativeModules);
    InAppPush.multiply(3, 4)
      .then(rs => {
        console.log('RS', rs);
      })
      .catch(err => {
        console.log(err);
      });
  });

  return <Text>HI</Text>;
};

export default nativeF;

 */
